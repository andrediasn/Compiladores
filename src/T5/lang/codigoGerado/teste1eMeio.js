
main();

function main() {
    let n = 0;
    let q = 0;
    let w = 0;
    let z = 0;
    n = 13;
    q = 5;
    w = ((let)divMod(String.valueOf(1), n, q));
    z = ((2 * w) + 1);
    process.stdout.write('Z'.toString());
    process.stdout.write(':'.toString());
    process.stdout.write(z.toString());
    process.stdout.write('\n'.toString());
}

function divMod( n,  q) {
    let returns = [];
    returns.push((n / q));
    returns.push((n % q));
    return returns;
}
  