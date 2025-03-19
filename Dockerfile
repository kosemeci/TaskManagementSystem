#open jdk 23 kullanarak temel bir imaj oluşturuyoruz
FROM openjdk:23-jdk

#Çalışma dizini oluşturuyoruz
WORKDIR /app

#Projeyi build ettiğin JAR dosyasını kopyala
COPY target/task-manager-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

#uygulamayı başlat
CMD ["java","-jar","app.jar"]