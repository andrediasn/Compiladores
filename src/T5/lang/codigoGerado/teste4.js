
main();

let  Ponto {
    let  x;
    let  y;
}
function main() {
    Ponto p = null;
    p = new Ponto();
    p.x = 10.0f;
    p.y = 10.0f;
    process.stdout.write('('.toString());
    process.stdout.write(p.x.toString());
    process.stdout.write(','.toString());
    process.stdout.write(' '.toString());
    process.stdout.write(p.y.toString());
    process.stdout.write(')'.toString());
    process.stdout.write('\n'.toString());
}
  