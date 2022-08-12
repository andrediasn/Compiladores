
function  Ponto () {
    return{      
      'x': null,
      'y': null,
    }
}
function main() {
    let p;
    p = Ponto();
    p.x = 10.0.toFixed(1);
    p.y = 10.0.toFixed(1);
    process.stdout.write('('.toString());
    process.stdout.write(p.x.toString());
    process.stdout.write(','.toString());
    process.stdout.write(' '.toString());
    process.stdout.write(p.y.toString());
    process.stdout.write(')'.toString());
    process.stdout.write('\n'.toString());
}
main();  