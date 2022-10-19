
function main() {
    n = 13;
    q = 5;
    w = divMod(n, q)[1];
    z = ((2 * w) + 1);
    process.stdout.write('Z'.toString());
    process.stdout.write(':'.toString());
    process.stdout.write(z.toString());
    process.stdout.write('\n'.toString());
}

function divMod(n, q) {
    return [(n / q), (n % q)];
}

main();  