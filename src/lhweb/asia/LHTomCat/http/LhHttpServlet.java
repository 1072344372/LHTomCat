package lhweb.asia.LHTomCat.http;

import java.io.IOException;

/**
 * 罗汉httpservlet
 *
 * @author 罗汉
 * @date 2024/02/26
 */
public abstract class LhHttpServlet implements LhServlet {
    @Override
    public void service(LhRequest request, LhResponse response) throws IOException {
        // equalsIgnoreCase比较两个字符串是否相等，且忽略大小写
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            // this的动态绑定非常非常重要  谁调用的就是谁  真正的运行类型
            this.doGet(request, response);
        }else if ("POST".equalsIgnoreCase(request.getMethod())) {
            this.doPost(request, response);
        }else {//默认执行get方法
            this.doGet(request, response);
        }
    }

    // 模板设计模式
    // 让LhHttpServlet的子类去实现
    public abstract void doGet(LhRequest request, LhResponse response);

    public abstract void doPost(LhRequest request, LhResponse response);
}
