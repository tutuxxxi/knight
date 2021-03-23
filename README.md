# 骑手俱乐部 后端系统



## 技术栈介绍







## 功能接口介绍

------

### 用户模块（/user）





#### 用户注册接口：



##### 接口名称： http://39.108.233.198:8080/user/register (post)

##### 请求参数示例：

```json
// 此处仅仅展示三个必要字段，其余字段key值可以参考响应示例中的data结构
{
    "password": "123456789",
    "tel": "19999999999",
    "nickName": "王五"
}
```

##### 响应示例：

```json
{
    "code": 500,
    "msg": "手机号已被注册过",
    "data": null
}

{
    "code": 500,
    "msg": "注册信息不完整",
    "data": null
}

{
    "code": 500,
    "msg": "未知错误",
    "data": null
}
// tips 前端无法获取到用户的密码和加密盐
{
    "code": 200,
    "msg": "SUCCESS",
    "data": {
        "id": "b836b4e8-d845-4445-b374-f2b9717f18e9",
        "password": null,
        "salt": null,
        "tel": "19999999999",
        "email": null,
        "nickName": "王五",
        "age": null,
        "gender": null,
        "profile": null,
        "signature": null
    }
}
```







#### 用户登陆验证接口：



##### 接口名称： http://39.108.233.198:8080/user/login (get)

##### 请求参数示例：

```json
// 两个均为必要字段
{
    "password": "123456789",
    "tel": "19999999999"
}
```

##### 响应示例：

```json
{
    "code": 500,
    "msg": "账号或密码错误",
    "data": null
}

{
    "code": 500,
    "msg": "信息不完整",
    "data": null
}

{
    "code": 500,
    "msg": "未知错误",
    "data": null
}
// tips 前端无法获取到用户的密码和加密盐
{
    "code": 200,
    "msg": "SUCCESS",
    "data": {
        "id": "d7483ada-76cd-4df9-b1e8-97c2c50bc209",
        "password": null,
        "salt": null,
        "tel": "13999999999",
        "email": null,
        "nickName": "张三",
        "age": null,
        "gender": null,
        "profile": null,
        "signature": null
    }
}
```













#### 用户信息修改接口：



##### 接口名称： http://39.108.233.198:8080/user/update (post)

##### 请求参数示例：

```json
// 请注意， 此为复用接口，你只需要携带必要字段（id） 和 你期望修改的字段：
//   例如在用户上传头像时，只需要携带id和profile即可（profile的值由工具接口的文件上传接口返回提供）
{
    "id": "0bf10b1c-e134-4864-b5da-aca082421284",
    "tel": "12345565654",
    "password": "123456789",
    "profile": "5c3f1fff-bd6f-405c-a8c3-a3e5a04773cc",
    "gender": "男",
    "age": 20
}
```

##### 响应示例：

```json
{
    "code": 500,
    "msg": "图片信息丢失",
    "data": null
}

{
    "code": 500,
    "msg": "id错误或手机号已存在",
    "data": null
}

{
    "code": 500,
    "msg": "用户id缺失",
    "data": null
}
// tips 前端无法获取到用户的密码和加密盐
{
    "code": 200,
    "msg": "SUCCESS",
    "data": {
        "id": "0bf10b1c-e134-4864-b5da-aca082421284",
        "password": null,
        "salt": null,
        "tel": "12345565654",
        "email": null,
        "nickName": "李四",
        "age": 20,
        "gender": "男",
        "profile": "1616483848782.jpg",
        "signature": null
    }
}
```













## 

------

### 工具模块（/util）





#### 图像上传接口：



##### 接口名称： http://39.108.233.198:8080/util/uploadImages (post)

##### 请求表单示例：

```html
<form method="post" enctype="multipart/form-data" action="http://39.108.233.198:8080/util/uploadImages">
        <input type="file" name="files" multiple>
        <input type="submit">
</form>
```

##### 响应示例：

```json
{
    "code": 500,
    "msg": "上传失败",
    "data": null
}

{
    "code": 500,
    "msg": "上传内容为空",
    "data": null
}

// 上传成功后的key不可被忽略， 需要伴随附属信息一起返回
//   例如用户上传动态时，应先进行图片上传，成功后拿到key，再将key和内容重新上传（头像也为相同操作）
{
  "code":200,
  "msg":"SUCCESS",
  "data":"5c3f1fff-bd6f-405c-a8c3-a3e5a04773cc"
}
```

