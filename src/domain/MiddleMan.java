package domain;

/**
 * Created by Bas.Bouwhuis on 2/23/2017.
 */
public class MiddleMan
{
    public class Foo {
        Bar bar;

        public Bar getBar()
        {
            return bar;
        }
    }

    public class Bar {
        private Foo impValue1;
        public Bar(Foo impValue) {
            impValue1 = impValue;
        }
        public Foo getImpValue() {
            return impValue1;
        }
    }

    public class Client {
        Foo a;
        Foo impValue = a.getBar().getImpValue();
    }
}
