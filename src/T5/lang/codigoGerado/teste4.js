
main();

let  Ponto = {
    'x': null,
    'y': null,
}
function main() {
    let p = null;
    p = Ponto;
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
  