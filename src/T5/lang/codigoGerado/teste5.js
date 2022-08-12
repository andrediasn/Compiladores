
function  Ponto () {
    return{      
      'x': null,
      'y': null,
    }
}
function main() {
    let i;
    let k;
    let p;
    k = 5;
    p = [];
    i = 0;
    for(let it17_3 = k; it17_3 > 0; it17_3--) {

        p[i] = Ponto();
        p[i].x = (12 + i);
        p[i].y = (12 - i);
        i = (i + 1);
    }
    process.stdout.write('\n'.toString());
}
main();  