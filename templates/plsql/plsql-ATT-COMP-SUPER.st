plsql-ATT-COMP-SUPER(code, when, table, subTemplate)::= 
"
create or replace trigger $code$
     $when$ on $table$
        for each row
		declare
			l_passed boolean := true;
			l_error_stack varchar2(4000);
		begin
			l_passed := $subTemplate$;
			if not l_passed
				then
					l_error_stack := l_error_stack || 'Trigger $code$ has been violated';
				end if;
			if l_error_stack is not null
				then
					raise_application_error(-20800, l_error_stack);
				end if;
	
end $code$;		
"