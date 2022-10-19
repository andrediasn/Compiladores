
function fat(n) {
    if((n == 0)) {

        return [1];
    } 
    return [(n * fat((n - 1))[0])];
}

function spook(n) {
    if((n == 1)) {

        return [(2 * n)];
    } 
    if((n == 2)) {

        return [((2 * n) + 1)];
    } 
    return [(n - 1)];
}

function main() {
    k = fat(6)[0];
    k = spook(2)[0];
    process.stdout.write(k.toString());
    process.stdout.write('\n'.toString());
}

main();  