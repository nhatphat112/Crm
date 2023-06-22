package utils;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class FileUploadUtil {

    private String uploadDirectory; // Đường dẫn tới thư mục lưu trữ file

    public FileUploadUtil(String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }

    public String upload(HttpServletRequest request) throws Exception {
        // Kiểm tra xem request có phải là multipart/form-data không
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new Exception("Yêu cầu phải là multipart/form-data.");
        }
        // Kiểm tra và tạo thư mục lưu trữ nếu nó chưa tồn tại
        File uploadDir = new File(uploadDirectory);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        // Tạo một đối tượng DiskFileItemFactory để quản lý việc lưu trữ file tạm thời trên ổ đĩa
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*1024); // Kích thước file tối đa được lưu trữ tạm thời (1 MB)
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Tạo một đối tượng ServletFileUpload để xử lý yêu cầu upload
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024*1024*10); // Kích thước tối đa của mỗi file (10 MB)
        upload.setSizeMax(1024*1024*50); // Kích thước tối đa của toàn bộ các file (50 MB)

        try {
            // Parse các file được upload từ request
            FileItemIterator iter = upload.getItemIterator(request);

            // Lưu file được upload vào thư mục lưu trữ
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                if (!item.isFormField()) { // Nếu item là file được upload
                    String originalFileName = item.getName();
                    String contentType = item.getContentType();
                    String uniqueFileName = generateUniqueFileName(originalFileName); // Tạo tên file unique
                    String filePath = this.uploadDirectory + File.separator + uniqueFileName;
                    File uploadedFile = new File(filePath);
                    InputStream inputStream = item.openStream();
                    OutputStream outputStream = new FileOutputStream(uploadedFile);
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    inputStream.close();
                    outputStream.close();
                    return uniqueFileName; // Trả về tên file unique
                }
            }
        } catch (Exception e) {
            throw new Exception("Lỗi: " + e.getMessage());
        }
        return null;
    }

    private String generateUniqueFileName(String originalFileName) {
        String extension = "";
        int lastDotIndex = originalFileName.lastIndexOf(".");
        if (lastDotIndex >= 0) {
            extension = originalFileName.substring(lastDotIndex);
        }
        String uniqueName = UUID.randomUUID().toString() + extension;
        return uniqueName;
    }
}
