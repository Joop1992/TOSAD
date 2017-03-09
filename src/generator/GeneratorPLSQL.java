package generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import domain.MiddleMan;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import controller.Controller;
import domain.BusinessRule;
import domain.Value;

public class GeneratorPLSQL implements Generator
{
    public static final String SUPER = "-SUPER";
    private STGroup group = new STGroupDir("C:/Users/Ivar/Workspace/TOSAD/templates/plsql", '$', '$');
    private ST superTemplate;
    private int count = 0;
    private String nameAbbreviation = ""; // ATT, ENT etc.
    private String typeAbbreviation = ""; // COMP, LIST, RANG, etc.
    private Controller controller = new Controller();

    private boolean isSizeAboveOne(List<?> list)
    {
        return list.size() > 1;
    }

    // returns the stringtemplate of
    @Override
    public ST getSuperTemplate(String name, String type)
    {
        // retrieving name abbrevation (ATT, ENT etc.)
        nameAbbreviation = name.substring(0, 3).toUpperCase();

        // retrieve type abbreviation (COMP, LIST, RANG etc)
        typeAbbreviation = type.substring(0, 4).toUpperCase();

        String superTemplate = "plsql-" + nameAbbreviation + "-" + typeAbbreviation + SUPER;
        return group.getInstanceOf(superTemplate);
    }


    // method which returns the subtemplate used in the super template
    private String getSubTemplate(BusinessRule br)
    {
        return new TemplateGetter(br).invoke();
    }

    // method that retrieves the super and (if neccessary, multiple)
    // subtemplate(s)
    public String fillTemplate(BusinessRule br)
    {
        if (br != null)
        {
            String subTemplate = "";
            String declareSubTemp = "";
            String beginSubTemp = "", test = "";
            String table = br.getTable(), when = br.getWhen(), code = br.getCode();
            superTemplate = getSuperTemplate(, br, );

            // als target code bestaat substring(0, code.length()-3)
            List<String> existingTriggers = controller.getExistingTriggers(code);

            if (!existingTriggers.isEmpty() || existingTriggers.size() != 0)
            {

                // voor elke haal alle bestaande uit de tooldatabase
                List<BusinessRule> triggBusinessRules = new ArrayList<BusinessRule>();
                for (String trigger : existingTriggers)
                {
                    BusinessRule rule2 = controller.searchByCode(trigger);
                    if (rule2 != null)
                    {
                        triggBusinessRules.add(controller.searchByCode(trigger));
                    }
                }
                triggBusinessRules.add(br);
                for (int i = 0; i < triggBusinessRules.size(); i++)
                {
                    BusinessRule rule = triggBusinessRules.get(i);
                    // System.out.println("allaam\n"+rule.getCode()+"\n
                    // -------------");
                    if (i != 0)
                    {
                        test = getSubTemplate(rule);
                        // System.out.println("\nSTEP
                        // 12:"+test+"\n------------");
                        if (test.contains("````````````````````````*&`"))
                        {
                            List<String> strings = Arrays.asList(Pattern
                                    .compile(
                                            "\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\*\\&\\`")
                                    .split(test));
                            declareSubTemp += strings.get(0);
                            beginSubTemp += " and " + strings.get(1);
                            System.out.println("1\n" + declareSubTemp);
                            System.out.println("2\n" + beginSubTemp);
                            // System.out.println("yes again");
                        } else
                        {
                            // System.out.println("nope again");
                            subTemplate += " and " + test;
                        }
                    } else
                    {
                        System.out.println("nasw");
                        test = getSubTemplate(rule);
                        // System.out.println("\nSTEP
                        // 1:"+test+"\n------------");
                        if (test.contains("````````````````````````*&`"))
                        {
                            // System.out.println("yes");
                            List<String> strings = Arrays.asList(Pattern
                                    .compile(
                                            "\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\*\\&\\`")
                                    .split(test));
                            declareSubTemp += strings.get(0);
                            beginSubTemp += strings.get(1);
                            System.out.println("3\n" + declareSubTemp);
                            System.out.println("4\n" + beginSubTemp);
                        } else
                        {
                            // System.out.println("nope");
                            subTemplate += test;
                        }
                    }
                }
                // generaten en aan subtemplate plakken

                // creating the superTemplate
                superTemplate.add("table", table);
                System.out.println("\n\n\n" + table + "\n\n\n\n");
                superTemplate.add("when", when);
                superTemplate.add("code", code);

                // add the finalSubTemplate to our super template
                try
                {
                    addSubToSuperTemplate(subTemplate);
                } catch (IllegalArgumentException iae)
                {
                }

                try
                {
                    superTemplate.add("declareSub", declareSubTemp);
                    superTemplate.add("beginSub", beginSubTemp);
                } catch (IllegalArgumentException iae)
                {

                }

                return superTemplate.render();

            } else
            {
                test = getSubTemplate(br);
                // System.out.println("\nSTEP 1:"+test+"\n------------");
                if (test.contains("````````````````````````*&`"))
                {
                    // System.out.println("yes");
                    List<String> strings = Arrays.asList(Pattern
                            .compile(
                                    "\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\`\\*\\&\\`")
                            .split(test));
                    declareSubTemp += strings.get(0);
                    beginSubTemp += strings.get(1);
                } else
                {
                    subTemplate = test;
                }
                // if its the only businessrule/trigger with this code:
                // construct super template
                superTemplate.add("table", br.getTable());
                superTemplate.add("when", br.getWhen());
                superTemplate.add("code", br.getCode());
                // construct sub template and add it to the supertemplate
                String sub = getSubTemplate(br);

                try
                {
                    addSubToSuperTemplate(subTemplate);
                } catch (IllegalArgumentException iae)
                {
                }

                try
                {
                    superTemplate.add("declareSub", declareSubTemp);
                    superTemplate.add("beginSub", beginSubTemp);
                } catch (IllegalArgumentException iae)
                {

                }
                return superTemplate.render();
            }
        } else
        {
            return "";
        }
    }

    private void addSubToSuperTemplate(String subTemplate) {
        superTemplate.add("subTemplate", subTemplate);
    }

    private class TemplateGetter
    {
        private BusinessRule br;

        public TemplateGetter(BusinessRule br)
        {
            this.br = br;
        }

        public String invoke()
        {
            count++;

            MiddleMan man = new MiddleMan();

            int newCount = man.getId();

            // retrieve type abbreviation (COMP, LIST, RANG etc)
            typeAbbreviation = getAbbreviation().substring(0, 4).toUpperCase();

            // retrieve operator abbreviation (NOT, GRE, LES etc.)
            String operatorAbbreviation = br.getOperator().getAbbreviation().toUpperCase();

            // construct the template name (f.e. plsql-ATT-COMP-EQU
            String template = "plsql-" + nameAbbreviation + "-" + typeAbbreviation + "-" + operatorAbbreviation;

            // StringTemplate.org -- providing hardcoded group directory, custom
            // delimiters
            STGroup group = new STGroupDir("C:/Users/Ivar/Workspace/TOSAD/templates/plsql", '$', '$');
            System.out.println(template);
            // retrieving the sub template with our previously constructed name
            ST subTemplate = group.getInstanceOf(template);

            // not all rules have a target (f.e. other) so try-catch
            fillSubTemplate(subTemplate);

            List<Value> values = br.type.getValues(br);
            String firstValue = values.get(0).getName();
            // System.out.println(firstValue);
            subTemplate.add("firstValue", firstValue);
            // not all rules have multiple values (f.e. greater than) so try-catch
            String lastValue = null;
            if (isSizeAboveOne(values))
            {

                lastValue = values.get(1).getName();
                // System.out.println("sick shit"+ lastValue);
                subTemplate.add("lastValue", lastValue);
            }
            System.out.println(subTemplate.render());
            return subTemplate.render();
        }

        private void fillSubTemplate(ST subTemplate)
        {
            try
            {
                String target = ":new." + br.getAttribute().getName();
                subTemplate.add("target", target);
            } catch (NullPointerException | IllegalArgumentException a)
            {
                // do nothing, template doesn't contain a target
            }
            try
            {
                subTemplate.add("amount", String.valueOf(count));
            } catch (NullPointerException | IllegalArgumentException a)
            {
                // do nothing, template doesnt contain an amount.
            }
        }

        private String getAbbreviation()
        {
            Scanner sc = new Scanner(br.getRuleTypeName());
            String name = sc.next();
            String type = sc.next();
            sc.close();
            // retrieving name abbrevation (ATT, ENT etc.)
            nameAbbreviation = name.substring(0, 3).toUpperCase();
            return type;
        }
    }
}
