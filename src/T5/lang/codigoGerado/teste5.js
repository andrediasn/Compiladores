
main();

static class Ponto {
    let  x;
    let  y;
}
function main() {
    let i = 0;
    let k = 0;
    Ponto[] p = null;
    k = 5;
    p = new Ponto[k];
    i = 0;
    for(let it17_3 = k; it17_3 > 0; it17_3--) {

        p[i] = new Ponto();
        p[i].x = (12 + i);
        p[i].y = (12 - i);
        i = (i + 1);
    }
    process.stdout.write('\n'.toString());
}
  