package org.example;

import entities.*;
import dao.*;
import lombok.SneakyThrows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        connection.getConnection();
        loginIn();

    }

    @SneakyThrows
    private static void loginIn() {
        Scanner sc1 = new Scanner(System.in);
        boolean flag = true;
        String id;
        String idNumber;
        String name;
        String username;
        String pwd;
        String realName;
        String phoneNumber;
        String address;
        String description;
        boolean sex;
        boolean flag2=true;
        int choice;
        while (flag) {
            String welcomeLine = "==============================";
            String welcomeWord1 = """
                    欢迎使用饿了么外卖平台
                    用户登录请按1\t商家登录请按2
                    管理员登录请按3
                    用户注册请按4\t商家注册请按5
                    管理员注册请按6
                    退出请按0""";
            System.out.println(welcomeLine);
            System.out.println(welcomeWord1);

            choice = sc1.nextInt();
            flag2=true;
            switch (choice) {
                case 0 -> {
                    System.out.println("感谢使用，再见");
                    flag = false;

                }
                case 1 -> {//todo 用户登录
                    CustomerDao cDao= new CustomerDao();
                    Customer customer = new Customer();
                    while (flag2){
                        System.out.println("请输入身份证号");
                        idNumber=sc1.next();
                        System.out.println("请输入密码");
                        pwd=sc1.next();
                        customer =cDao.login(idNumber,pwd);
                        if (customer == null) {
                            System.out.println("登录失败！输入0退出，输入任意键重试");
                            if(sc1.nextLine().equals("0")){
                                flag2=false;
                            }
                        } else {
                            System.out.println("欢迎用户" + customer.getUsername());
                            flag2=false;
                            System.out.println("(用户后续功能)");
                            //todo 用户后续功能
//                            cDao.showMenu();
                        }
                    }
                }
                case 2 -> {//todo 商家登录

                }
                case 3 -> {//todo 管理员登录
                    AdminDao aDao=new AdminDao();
                    Admin admin=new Admin();
                    while (flag2){
                        System.out.println("请输入管理员id：");
                        id=sc1.next();
                        System.out.println("请输入管理员密码：");
                        pwd=sc1.next();
                        admin = aDao.login(id, pwd);
                        if (admin == null) {
                            System.out.println("登录失败！输入0退出，输入任意键重试");
                            if(sc1.nextLine().equals("0")){
                                flag2=false;
                            }
                        } else {
                            System.out.println("欢迎管理员" + admin.getName());
                            flag2=false;
                            System.out.println("(管理员后续功能)");
                            //todo 管理员后续功能
//                            aDao.showMenu();
                        }
                    }

                }
                case 4 -> {//todo 用户注册
                    CustomerDao cDao = new CustomerDao();
                    Customer customer = new Customer();
                    System.out.println("请输入身份证号：");
                    customer.setIdNumber(sc1.next());
                    System.out.println("请输入密码：");
                    customer.setPassword(sc1.next());
                    System.out.println("请输入用户名：");
                    customer.setUsername(sc1.next());
                    System.out.println("请输入地址");
                    customer.setAddress(sc1.next());
                    System.out.println("请输入性别");
                    if(sc1.next().equals("男")){
                        customer.setSex(true);
                    }
                    else{
                        customer.setSex(false);
                    }
                    System.out.println("请输入真实姓名：");
                    customer.setRealName(sc1.next());
                    System.out.println("请输入电话号码：");
                    customer.setPhoneNumber(sc1.next());
                    cDao.regist(customer.getUsername(),customer.getPassword(),customer.getAddress(),
                            customer.getSex(),customer.getPhoneNumber(),customer.getRealName(), customer.getIdNumber());



                }
                case 5 -> {//todo 商家注册

                }
                case 6 -> {//管理员注册
                    AdminDao aDao=new AdminDao();
                    Admin admin=new Admin();
                    System.out.println("请输入要注册的名称：");
                    name=sc1.next();
                    System.out.println("请输入密码：");
                    pwd=sc1.next();
                    aDao.regist(name,pwd);


                }
                default -> {
                    System.out.println("输入无效，请重新输入！");
                }

            }
        }
    }
}

/* 　　        ▃▆█▇▄▖
　　     　▟◤▖　　　◥█▎
　　　◢◤　     ▐　　　　▐▉
　▗◤　　　  ▂　▗▖　　▕   █▎
　◤　  ▗▅▖◥▄　▀◣　　  █▊
▐　   ▕▎◥▖◣◤　　　　◢██
█◣　  ◥▅█▀　　　　 ▐██◤
▐█▙▂　　　      ◢██◤
　◥██◣　　　◢▄◤
　　　▀██▅▇▀
太痛苦力

*/
