plsql-ATT-LIST-INL(target, firstValue)::= 
"
		declare
			l_passed boolean := true;
			l_error_stack varchar2(4000);

			type type_coll_varchar2
			is table of varchar2(50);

			t_values type_coll_varchar2 := type_coll_varchar2($firstValue$);
		begin
			if $target$ member of t_values then
				l_passed := true;
			end if;
			if l_passed then
				l_error_stack := l_error_stack || 'Deze waarde mag wel/niet in de database gezet worden.';
			end if;
			if l_error_stack is not null
			then
				raise_application_error ( -20800, l_error_stack );
			end if;
		
"