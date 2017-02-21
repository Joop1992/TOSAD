plsql-TUP-OTHE-SUPER(declareSub, beginSub, code, when, table) ::= 
"
create or replace trigger $code$
     $when$ on $table$
        for each row
		declare
			$declareSub$
		begin
			$beginSub$
end $code$;

"