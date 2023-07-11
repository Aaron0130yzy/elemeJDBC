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
        String pwd;
        String realName;
        String phoneNumber;
        String address;
        String description;
        boolean sex;
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

            int choice = sc1.nextInt();
            Boolean flag2=true;
            switch (choice) {
                case 0 -> {
                    System.out.println("感谢使用，再见");
                    flag = false;

                }
                case 1 -> {//todo 用户登录

                }
                case 2 -> {//todo 商家登录

                }
                case 3 -> {//todo 管理员登录
                    AdminDao aDao=new AdminDao();
                    Admin admin=new Admin();
                    while (flag2){
                        System.out.println("请输入管理员id：");
                        id=sc1.nextLine();
                        System.out.println("请输入管理员密码：");
                        pwd=sc1.nextLine();
                        admin = aDao.login(id, pwd);
                        if (admin == null) {
                            System.out.println("登录失败！输入0退出，输入任意键重试");
                            if(sc1.nextLine().equals("0")){
                                flag2=false;
                            }
                        } else {
                            System.out.println("欢迎管理员" + admin.getName());
                            flag2=false;
                            System.out.println("管理员后续功能");
                            //todo 管理员后续功能
//                            aDao.showMenu();
                        }
                    }

                }
                case 4 -> {//todo 用户注册

                }
                case 5 -> {//todo 商家注册

                }
                case 6 -> {//todo 管理员注册


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
