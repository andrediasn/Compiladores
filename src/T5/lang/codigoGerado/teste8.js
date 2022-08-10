
main();

function fat( n) {
    let returns = [];
    if((n == 0)) {

        returns.push(1);
        return returns;
    } 
    returns.push((n * fat((n - 1))[0]));
    return returns;
}

function spook( n) {
    let returns = [];
    if((n == 1)) {

        returns.push((2 * n));
        return returns;
    } 
    if((n == 2)) {

        returns.push(((2 * n) + 1));
        return returns;
    } 
    returns.push((n - 1));
    return returns;
}

function main() {
    let k = 0;
    k = fat(6)[0];
    k = spook(2)[0];
    process.stdout.write(k.toString());
    process.stdout.write('\n'.toString());
}
  