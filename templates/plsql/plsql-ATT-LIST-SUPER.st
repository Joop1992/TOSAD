plsql-ATT-LIST-SUPER(code, when, table, declareSub, beginSub)::= 
"
create or replace trigger $code$
     $when$ on $table$
        for each row
		declare
			l_passed boolean := true;
			l_error_stack varchar2(4000);

			$declareSub$
		begin
			if $beginSub$ 
				then
					l_passed := true;
				end if;

			if l_passed 
				then
					l_error_stack := l_error_stack || 'This value may or may not be entered in the database';
				end if;

			if l_error_stack is not null
				then
					raise_application_error ( -20800, l_error_stack );
				end if;
end $code$;
		
"