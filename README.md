# RPS
人才管理系统

src
  ++main
    ++java
      ++com.hdu.rps
        ++mapper
        ++model
        ++service
          ++serviceImpl
        ++web
       ++RpsApplication.java
    ++resources
      ++mapper
      ++static
      ++templates
      ++application.properties
      ++generatorConfig.xml
    ++webapp
      ++WEB-INF
        ++jsp
          ++images
          ++**.jsp

使用到了spring boot框架,mysql数据库,myBatis操作数据库（使用了插件自动生成model,mapper）,devtools实现热部署,jsp显示用户界面
