/**
 * Created by lvjiawei on 2017/7/8.
 */
package com.actions.Interceptor;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import com.entity.UsersEntity;

public class isAdmin extends AbstractInterceptor{
    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        Map session = ai.getInvocationContext().getSession();
        System.out.println("auth check");
        if(session.containsKey("is_admin"))
        {
            String admin =  session.get("is_admin").toString();
            if(admin.equals("admin"))
                return ai.invoke();
            else
            {
                System.out.println("403");
                return "403";
            }
        }
        return Action.LOGIN;
    }

}
