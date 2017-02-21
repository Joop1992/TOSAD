plsql-ATT-LIST-INL(amount, target, firstValue) ::=
"type type_coll_varchar2_$amount$
is table of varchar2(50);

t_values_$amount$ type_coll_varchar2_$amount$ := type_coll_varchar2_$amount$($firstValue$);
````````````````````````*&` $target$ member of t_values_$amount$"