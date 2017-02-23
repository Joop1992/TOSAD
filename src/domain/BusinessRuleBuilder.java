package domain;

public class BusinessRuleBuilder
{
    private Attribute att;
    private String code;
    private String desc;
    private String tab;
    private String when;

    public BusinessRuleBuilder setAtt(Attribute att)
    {
        this.att = att;
        return this;
    }

    public BusinessRuleBuilder setCode(String code)
    {
        this.code = code;
        return this;
    }

    public BusinessRuleBuilder setDesc(String desc)
    {
        this.desc = desc;
        return this;
    }

    public BusinessRuleBuilder setTab(String tab)
    {
        this.tab = tab;
        return this;
    }

    public BusinessRuleBuilder setWhen(String when)
    {
        this.when = when;
        return this;
    }

    public BusinessRule createBusinessRule()
    {
        return new BusinessRule(att, code, desc, tab, when);
    }
}