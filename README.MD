Exemplos que não estavam a funcionar correctactemente na discussão e que se comportam como deveriam agora.

```
#!python

{
    val x = alloc(1);

    function g(h) {
        return h(*x*10);
    }

    while(*x<10) {
        print(g(function(y) { return y?1+y:2})); << ERRO DE TIPOS
        x = *x + 1;
    }

}
END


{ 
    function add( x, y ) { return x+y }

    print(add(1,true)) << ERRO DE TIPOS
}
END


{
    val x = {a:alloc(1),
             c:function(a){ return *(this.a) },
             b:function(a){this.a=(this.a)+a;return *(this.a)}}; << ERRO DE TIPOS

    print(*(x.a));
    print((x.c)(0));
    print((x.b)(3));
    print(*(x.a));
}
END

```
```
#!python

EXEMPLOS DEMONSTRATIVOS:
EXEMPLO FUNCAO:

COM ANOTACOES:

{
    val x = alloc(1);
    var y = 10;

    function f(h : fun(int,int;bool)) { return h(*x,*y); }

    while(*x<10) {
        print(f(function(a : int, b : int) { return a>b; }));
        x = *x + 1;
        y = *y - 1;
    }

}
END

POR EXEMPLO, MUDANDO O TIPO DE RETORNO DARA UM ERRO DE TIPOS:

{
    val x = alloc(1);
    var y = 10;

    function f(h : fun(int,int;int)) { return h(*x,*y); } << ERRO DE TIPOS

    while(*x<10) {
        print(f(function(a : int, b : int) { return a>b; }));
        x = *x + 1;
        y = *y - 1;
    }

}
END


SEM ANOTACOES:

{
    val x = alloc(1);
    var y = 10;

    function f(h) { return h(*x,*y); }

    while(*x<10) {
        print(f(function(a, b) { return a>b; }));
        x = *x + 1;
        y = *y - 1;
    }

}
END

EXEMPLO OBJECTO:

{
    val o = {a:alloc(1),
            b:function(a, b) { this.a = *(this.a) + 1; return *(this.a)+a+b; }};

    val x = alloc(1);
    var y = 10;

    while(*x<10) {
        print( (o.b)(*x,*y) );
        x = *x + 1;
        y = *y**x;
    }

}
END


EXEMPLO FILTRAGEM E LISTA:

{
    val x = {a:1,
            b:{ as:[{n:"0",v:0},
                    {n:"1",v:1},
                    {n:"2",v:2}],
                bs:[1,2,3,4] }};

    val bs =alloc( x.b.bs);

    for(u : x.b.as, u.v > 0 ) {
        bs = *(bs)@u.v*10;
        print(u.n);
    };
        print("LISTA FINAL: ");
        print(*bs);

}
END
```