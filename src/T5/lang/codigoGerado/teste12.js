
function even(n) {
    if((n == 0)) {
        return [true];
    } else {
        return [odd((n - 1))[0]];
    }

}

function odd(n) {
    if((n == 0)) {
        return [false];
    } else {
        return [even((n - 1))[0]];
    }

}

function main() {
    r = even(3654)[0];
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

main();  