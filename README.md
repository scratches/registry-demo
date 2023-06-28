Spring AOT does not support bean definitions created with a `Supplier` or with a `FactoryBean`:

```
$ mvn -P native native:compile
...
Caused by: java.lang.IllegalArgumentException: Code generation does not support com.example.demo.Foo
        at org.springframework.beans.factory.aot.BeanDefinitionPropertyValueCodeGenerator.generateCode(BeanDefinitionPropertyValueCodeGenerator.java:134)
        at org.springframework.beans.factory.aot.BeanDefinitionPropertyValueCodeGenerator.generateCode(BeanDefinitionPropertyValueCodeGenerator.java:99)
        ... 31 more
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
...
```

The `supplier` branch shows how to use a `Supplier` to create a bean definition, and exclude it from the AOT processor.
