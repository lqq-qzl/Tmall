package com.xq.tmall.tmall_1.config.alipay;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102500760291";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtc4LluDOvgd4aklQdYBYsdyXm0r753iW/LgaxsXFhwA5FBAxoK69Vu/WqkvFVtz+6Cz7wmCPJA0CRU12AGDlBAaQYBgNnUZ0O/KZOmMLg6OUepndtJFd2USvnyUoSOT7tY2LzM+Q4RKgWM30FQyH1zPOg4lm9FjRX7+QcDWqJmRGPmgtC5WwheniZhcRpQemGOhOKbpDljSrhqJghYF2iH1XX8i3fX+lT5Fmy8KrTYVmzYsyMpvNzCGHd64NKdIjSjndVL+F75ttJdkApFG+oQexoSBJAgSsxhv62ODBfgl52bYeORnghYJoakP4u0UUxi6GXroKzzr348361SscrQIDAQAB";
    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1zguW4M6+B3hqSVB1gFix3JebSvvneJb8uBrGxcWHADkUEDGgrr1W79aqS8VW3P7oLPvCYI8kDQJFTXYAYOUEBpBgGA2dRnQ78pk6YwuDo5R6md20kV3ZRK+fJShI5Pu1jYvMz5DhEqBYzfQVDIfXM86DiWb0WNFfv5BwNaomZEY+aC0LlbCF6eJmFxGlB6YY6E4pukOWNKuGomCFgXaIfVdfyLd9f6VPkWbLwqtNhWbNizIym83MIYd3rg0p0iNKOd1Uv4Xvm20l2QCkUb6hB7GhIEkCBKzGG/rY4MF+CXnZth45GeCFgmhqQ/i7RRTGLoZeugrPOvfjzfrVKxytAgMBAAECggEBAKsIehKozxYqxPeInS7NXaOF2QJ5Gl56LeD1+grBh8AnT4s2aNm3Sdx9qT9wJOhA43Xcy8PjPwY1TsuPwdE9VnXhDvW36yboXvT+l3Q8NVrUCx0Dn3T9xIJfgbCALlPVR49W6Av3wV5cPYkNx+/9Z9XmoQNRwg9hdydSejl14upVl7VmEw3OZmhaCTGd43J2jw2o5Kn5YVlCx7+80vnPhgu+/DoLxGPt2Lh1WJZnoP8j4Qv11HU/S14vB65MWNNGwrYN/vXqRtStD8QJ1GsYS+uoBHjmCp87/n1OmcNjL2VxWcZbqznO41leLFeKMJN97FzHZdt8u24aCA+/oLgL8OkCgYEA7IeVzibLzM/NNlfYQeLR1KaA+2+KT/1Nle175y8kYz5BMYSvCL2SYXvZ9gSOVUxnKRb7Y3/9tY/E+YY8M8axicX5lq9KI1fMFZjQK3+GLInLVgG0rAxSJ7h2wndfsG8H+oZ8wbj59FUC5HvC9v2G6vzUV1Vs1hFtqXItasI76b8CgYEAxMU9NpwJNbB60jDg6MDhqcz9Aj7kPjnw9ED/Ig4t+Sz3R48l8khNAxfHevXbkpcTVAtau3GR9ZmrEJ2lEwvZh9VIVdgAmN3dyjJvYcAI618k1ts6ASwMETwQAI8GZiJ6AYGXAwoNWWIPkJjZnUaY72dT+kqWoToL5WTdVz+NHJMCgYB+sLTlwdQBdQCG7hBwYexgSXrmsjzUUWngN17ZWl/UJIFI47fZJ6kjO/JoGtKeD5ll4eBu0Bx1xncL7AGM/g0ULY1dT7CXM6ylmL9PvTOB4aCfBbTfPo42plA+IzCG0UHlx2lFXi3L2Ej/Vub74poEfb3CtYfhNV6WlwiKmVT8AwKBgFax/H1yFFC2d/biGEYUrZiNu6/gm9ma18IWXRLZIoJVwIGj3Xk5R80kDfzsFBkrPRXbhtdte3y1fvxsKM4Se35wh1VjPSgdINo0jISKAqQJoIaVwqG/kXWKfBZy7cPUTuMZALsTE7ydCvSPk9nKwBlC8molsOZKV/FzFl6ZnofHAoGARKfPR3AKO7Q+TCShbK7C69CrG9Mbai+5SLD7iL4jtjsEXUEXxi+qH3VjDmLxU+zdq5qojywwdx16vvl6x3RM+I/D8APT/aFxoEvXWrnj3h3xXGE+GSsvp8G4MAxxV1/S/eVAwlRPwkmf7U40nG2HwHsD41Lgz29d2pR5LUzdNvk=";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.baidu.com";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8082/tmall/re";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
}

