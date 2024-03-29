// package lhweb.asia.LHTomCat.thread;
//
// import lhweb.asia.LHTomCat.http.LhRequest;
// import lhweb.asia.LHTomCat.http.LhResponse;
// import lhweb.asia.LHTomCat.http.LhServlet;
// import lhweb.asia.LHTomCat.factory.ServletFactory;
//
// import java.io.File;
// import java.io.IOException;
// import java.net.Socket;
//
// /**
//  * 罗汉服务器线程
//  *
//  * @author 罗汉
//  * @date 2024/02/25
//  */
// @Deprecated
// public class LhServer implements Runnable {
//     // 定义socket
//     private Socket socket = null;
//
//     public LhServer(Socket socket) {
//         System.out.println("创建了一个线程:" + Thread.currentThread().getName());
//         this.socket = socket;
//     }
//
//     @Override
//     public void run() {
//         // 这里我们可以对客户端/浏览器进行IO编程/交互
//         // 开启一个读的线程 专门读取客户端发送的信息
//         while (true) {
//             try {
//                 // 这里我们可以通过LhRequest对象，返回数据给浏览器/客户端
//                 LhRequest lhRequest = new LhRequest(socket.getInputStream());
//
//                 // 这里我们可以通过LhResponse对象，返回数据给浏览器/客户端
//                 LhResponse lhResponse = new LhResponse(socket.getOutputStream());
//
//                 // 业务逻辑~  判断是走静态资源还是访问serverLet
//                 // 判断走serverLet uri:/LhTomCat/UserServlet  url:/LhTomCat/UserServlet?username=admina&password=123456
//                 String serverName = "";
//                 String serverLetName = "";
//                 System.out.println("uri:" + lhRequest.getUri());
//                 System.out.println("url:" + lhRequest.getUrl());
//                 //切割servlet名称
//                 if (null != lhRequest.getUri() && !"/".equals(lhRequest.getUri())) {
//                     System.out.println("lhRequest.getUri()" + lhRequest.getUri());// /views/LhTomCat/TrainServlet
//                     int indexOf = lhRequest.getUri().lastIndexOf("LhTomCat");//如果k的值不存在，则返回-1 。
//                     if (indexOf>=0){
//                         //获取服务器名称
//                         serverName = "LhTomCat";
//                         String substringUrl = lhRequest.getUri().substring(indexOf);
//                         //获取setvlet名称
//                         serverLetName = substringUrl.split("/")[1];
//                     }
//                 }
//                 // 一 走servlet
//                 if (lhRequest.getUri().split("/").length > 2 && "LhTomCat".equals(serverName)) {// todo servlet映射 这里是写死的方法 之后写活 例如xml+反射机制
//                     LhServlet lhServlet = ServletFactory.getInstance().getServlet(serverLetName);
//                     if (lhServlet != null) {
//                         lhServlet.service(lhRequest, lhResponse);
//                         break;
//                     }
//                 } else {
//                     // 二 走静态资源
//                     // 1 判断是否是“/”访问首页 如果是则跳转到首页
//                     String fileName = "/index.html";
//                     if ("/".equals(lhRequest.getUri())) {
//                         fileName = "12306/webapps/index.html";
//                     } else {
//                         fileName = "12306/webapps" + lhRequest.getUri();
//                     }
//                     // 2 访问静态资源
//                     File file = new File(fileName);
//                     if (file.exists()) {
//                         lhResponse.write(file);
//                         break;
//                     } else {
//                         file = new File("12306/webapps/404.html");
//                         lhResponse.write(file);
//                         break;
//                     }
//                 }
//             } catch (IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
//         // 关闭socket
//         closeSocket();
//     }
//
//     /**
//      * 关闭套接字
//      */
//     private void closeSocket() {
//         try {
//             socket.close();
//         } catch (IOException e) {
//             throw new RuntimeException(e);
//         }
//     }
// }
