#Sử dụng image base là OpenJDK
FROM openjdk:21-oracle

# Thêm metadata cho image
LABEL description="Common core service"
LABEL author="Brian Dang"

# Đặt thư mục làm việc trong container
WORKDIR /application

# Định nghĩa biến ARG để chỉ định file JAR
ARG JAR_FILE=target/*.jar

# Copy file JAR từ máy host vào container
COPY ${JAR_FILE} application.jar

# Mở cổng 8011 đẻ giao tiếp với container
EXPOSE 8011

# Khởi chạy ứng dụng khi container chạy
ENTRYPOINT ["java", "-jar", "application.jar"]