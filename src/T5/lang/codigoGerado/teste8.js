
main();

function fat( n) {
    let returns = [];
    if((n == 0)) {

        returns.push(1);
        return returns;
    } 
    returns.push((n * ((let)fat(String.valueOf(0), (n - 1)))));
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
    k = ((let)fat(String.valueOf(0), 6));
    k = ((let)spook(String.valueOf(0), 2));
    process.stdout.write(k.toString());
    process.stdout.write('\n'.toString());
}
  