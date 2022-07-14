thrift tutorial

https://thrift.apache.org/tutorial/java.html


thrift 生成代码
thrift -r -gen java tutorial.thrift

maven build:
mvn -U clean package


可选：
如果需要支持加密通道，需要用keytool生成 keystore和 truststore


生成keystore 和 truststore 文件：

生成keystore文件(保存私钥和证书)
keytool -genkey -alias 1 -keyalg RSA -validity 3650 -keystore local.keystore

查看:
keytool -list -v -keystore local.keystore -storepass thrift

导出证书：
keytool -export -alias 1 -keystore local.keystore -rfc -file local_cert.cer

将证书导入 truststore
keytool -import -alias 1 -file local_cert.cer  -keystore truststore.keystore
