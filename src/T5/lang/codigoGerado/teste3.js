
function main() {
    k = 4;
    x = [];
    x[0] = 0;
    x[3] = 15;
    process.stdout.write(x[3].toString());
    process.stdout.write('\n'.toString());
    i = 0;
    for(let it17_3 = k; it17_3 > 0; it17_3--) {

        if(((i % 2) == 0)) {

            x[i] = (2 * i);
        } else {

            x[i] = ((2 * i) + 1);
        }

        i = (i + 1);
    }
    i = 0;
    process.stdout.write('{'.toString());
    if((0 < k)) {

        process.stdout.write(x[0].toString());
        for(let it31_6 = (k - 1); it31_6 > 0; it31_6--) {

            process.stdout.write(','.toString());
            process.stdout.write(x[(i + 1)].toString());
            i = (i + 1);
        }
    } 
    process.stdout.write('}'.toString());
    process.stdout.write('\n'.toString());
}

main();  