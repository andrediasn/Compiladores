
function main() {
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

function divMod(n, q) {
    return [(n / q), (n % q)];
}

main();  