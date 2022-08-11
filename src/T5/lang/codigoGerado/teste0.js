
function main() {
    let i = 0;
    let nlines = 0;
    nlines = 5;
    i = nlines;
    for(let it16_3 = nlines; it16_3 > 0; it16_3--) {

        for(let it17_5 = i; it17_5 > 0; it17_5--) {

            process.stdout.write('*'.toString());
        }
        i = (i - 1);
        process.stdout.write('\n'.toString());
    }
}
main();  