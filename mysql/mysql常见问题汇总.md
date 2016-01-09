###1. collation 的问题
> 执行sql有时候会出现下面的错误.
Illegal mix of collations (utf8mb4_general_ci,IMPLICIT) and (utf8mb4_unicode_ci,IMPLICIT) for operation '='
问题排查:

首先查看

    show variables like 'collation%';
    Variable_name	Value
    collation_connection	utf8_general_ci
    collation_database	utf8mb4_unicode_ci
    collation_server	utf8mb4_unicode_ci
        
    show global variables like 'collation%';
    show charset like 'utf8';
    show collation like 'utf8%';
    
可以修改

    set collation_connection = utf8mb4_unicode_ci;
    set global collation_connection = utf8mb4_unicode_ci;
    
或者修改jdbc url:

    ?characterEncoding=UTF-8&connectionCollation=utf8mb4_unicode_ci
    
    
