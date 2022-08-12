
function main() {
    let n;
    let q;
    let quo;
    let res;
    n = (13).toFixed(0);
    q = (5).toFixed(0);

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
    returns.push((n / q).trunc(0));
    returns.push((n % q));
    return returns;
}
main();  