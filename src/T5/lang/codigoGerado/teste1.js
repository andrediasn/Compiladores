
main();

function main() {
    let n = 0;
    let q = 0;
    let quo = 0;
    let res = 0;
    n = 13;
    q = 5;

    quo = divMod(n, q)[0];
    res = divMod(n, q)[1];


    process.stdout.write('Q'.toString());
    process.stdout.write(':'.toString());
    process.stdout.write(quo.toString());
    process.stdout.write('\n'.toString());
    process.stdout.write('R'.toString());
    process.stdout.write(':'.toString());
    process.stdout.write(res.toString());
    process.stdout.write('\n'.toString());
}

function divMod( n,  q) {
    let returns = [];
    returns.push((n / q));
    returns.push((n % q));
    return returns;
}
  