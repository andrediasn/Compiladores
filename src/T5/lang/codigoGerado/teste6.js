
function  Ponto () {
    return{      
      'x': null,
      'y': null,
    }
}
function printP( p) {
    process.stdout.write('('.toString());
    process.stdout.write(p.x.toString());
    process.stdout.write(','.toString());
    process.stdout.write(p.y.toString());
    process.stdout.write(')'.toString());
}

function printV( v,  n) {
    let i;
    process.stdout.write('{'.toString());
    if((0 < n)) {

        printP(v[0]);

        i = 1;
        for(let it30_7 = (n - 1); it30_7 > 0; it30_7--) {

            process.stdout.write(','.toString());

            printP(v[i]);

            i = (i + 1);
        }
    } 
    process.stdout.write('}'.toString());
}

function sort( v,  n) {
    let aux;
    let i;
    let j;
    i = 0;
    for(let it42_4 = (n - 1); it42_4 > 0; it42_4--) {

        j = (i + 1);
        for(let it44_7 = (n - (i + 1)); it44_7 > 0; it44_7--) {

            if((v[i].x < v[j].x)) {

                aux = v[i];
                v[i] = v[j];
                v[j] = aux;
            } 
            j = (j + 1);
        }
        i = (i + 1);
    }
}

function main() {
    let i;
    let k;
    let p;
    k = 5;
    p = [];
    i = 0;
    for(let it60_3 = k; it60_3 > 0; it60_3--) {

        p[i] = Ponto();
        p[i].x = (12 + i);
        p[i].y = (12 - i);
        i = (i + 1);
    }

    printV(p, k);

    sort(p, k);

    process.stdout.write('\n'.toString());

    printV(p, k);

    process.stdout.write('\n'.toString());
}
main();  