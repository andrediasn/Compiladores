
main();

function even( n) {
    let returns = [];
    if((n == 0)) {
        returns.push(true);
        return returns;
    } else {
        returns.push(((let)odd(String.valueOf(0), (n - 1))));
        return returns;
    }

}

function odd( n) {
    let returns = [];
    if((n == 0)) {
        returns.push(false);
        return returns;
    } else {
        returns.push(((let)even(String.valueOf(0), (n - 1))));
        return returns;
    }

}

function main() {
    let r = false;
    r = ((let)even(String.valueOf(0), 3));
    if(r) {

        process.stdout.write('P'.toString());
        process.stdout.write('A'.toString());
        process.stdout.write('R'.toString());
    } else {

        process.stdout.write('I'.toString());
        process.stdout.write('M'.toString());
        process.stdout.write('P'.toString());
        process.stdout.write('A'.toString());
        process.stdout.write('R'.toString());
    }

}
  