
    const notificationLink = document.querySelector('.notification-link');

    const notification = document.querySelector('.notification');
    const closeBtn = document.querySelector('.close-btn');
    document.addEventListener('DOMContentLoaded', function(event) {
        if (notificationLink.textContent.trim() !== '') {
            notification.classList.add('show');
            console.log(notificationLink.textContent.toString())
        }

    });

    // Hiển thị thông báo
    console.log(notification.classList);
    // Tự động ẩn thông báo sau 4 giây

    setTimeout(function() {
        notification.classList.remove('show');
    }, 4000);

    // Xử lý sự kiện khi người dùng nhấn nút đóng

    closeBtn.addEventListener('click', function() {

        notification.classList.remove('show');
    });
