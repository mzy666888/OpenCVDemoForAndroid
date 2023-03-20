# OpenCVDemoForAndroid

#1、Download OpenCV

从[https://opencv.org/releases/](https://opencv.org/releases/)下载最新版（这个时候最新版为4.7.0版本），选择Android平台的库。
下载完解压后看到有文件夹：samples和sdk，samples是示例，sdk就是我们要用到的OpenCV库了。

#2、使用Android studio新建项目

打开AS，创建一个名为DemoForOpenCv的项目
![image](https://user-images.githubusercontent.com/2587129/226222107-9acb965a-bf0c-4c9c-bacf-b0d09ccc84e0.png)

选择Empty Activity模板，进入下一步。输入项目名称：DemoForOpenCv。
![image](https://user-images.githubusercontent.com/2587129/226222207-9111fd17-4c06-4d29-a05b-b12ada5e74c4.png)
等待加载完成，打开项目所在位置，将下载的sdk文件夹拷贝到DemoForOpenCv的根目录中


选择File->New-> Import Module

![image](https://user-images.githubusercontent.com/2587129/226222688-7d66a062-6fef-46bd-8332-e8e461877e73.png)

选择sdk后
![image](https://user-images.githubusercontent.com/2587129/226223266-fce2ddce-d4f3-48f4-9130-3134fce1fd1b.png)
出现这种情况，导致无法下一步继续进行。

此时，你可以这样：

找到settings.gradle文件
![image](https://user-images.githubusercontent.com/2587129/226223406-6344ca4d-5b49-45d4-8fea-74b6640b1b87.png)

添加如下代码：
```
include ':opencv'
include ':sdk'
include ':sdk'
```


找到sdk文件夹下的build.gradle文件，将`apply plugin: 'kotlin-android'`注释掉
![image](https://user-images.githubusercontent.com/2587129/226225428-38a6044e-5719-4ac9-a5e9-9f251ade617c.png)


然后就可以编译成功了。

编译过程中需要下载一些库文件，因此要确保网络畅通。
