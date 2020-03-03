package com.lovezz.aop;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lovezz.annotation.OperationEmailDetail;
import com.lovezz.entity.TbUser;
import com.lovezz.service.MailService;
import com.lovezz.service.TbUserService;
import com.lovezz.utils.RequestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 邮件通知切面
 * @Auther: liweijian
 * @Date: 2019/12/9 20:23
 * @Description:
 */

@Aspect
@Component
public class NoticeAspect {

    @Autowired
    private MailService mailService;
    @Autowired
    private TbUserService userService;

    /**
     * 切入点，切的是注解，也可以使用包名进行切入
     * '@Pointcut("execution(* com.lovezz.service.impl.*.*(..))")'
     */
    @Pointcut("@annotation(com.lovezz.annotation.OperationEmailDetail)")
    public void operationEmail(){}


    @Around("operationEmail()")
    public Object Interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;

        try {
            res = joinPoint.proceed();

            return res;
        }finally {
            //发送邮件
            sendMail(joinPoint);
        }
    }

    private void sendMail(ProceedingJoinPoint joinPoint){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        OperationEmailDetail annotation = signature.getMethod().getAnnotation(OperationEmailDetail.class);

        if (annotation != null){

            //内容
            String content = annotation.content();
            //操作模块
            String operationClass = annotation.operationClass();
            //邮件主题
            String subject = annotation.subject();

            //传入的发送列表为空，从数据库中查询
            List<String> emailList = Arrays.asList(annotation.to());
            if (emailList.size() == 0){

                //获取所有有效用户（排除当前用户）
                Map<String,Object> map = new HashMap<>();
                map.put("isDelete","0");
                map.put("id",new RequestUtils().getLoginUserId());
                emailList = userService.selectUserEmail(map);
            }

            for (String email: emailList) {
                content = content + "\r\n发布时间：" + LocalDateTime.now();
                mailService.sendSimpleMail(email, "【新消息通知】" + operationClass, content);
            }
        }
    }





}
