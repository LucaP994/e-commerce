import { HttpParams } from "@angular/common/http";

export class StringHelper {
    /**
     * Effettua una sostituzione di parametri da una stringa composta come nell'esempio
     * @example "my string as a param with val equals to {value}"
     * @param source stringa su cui operare la ricerca
     * @param replacements oggetto complesso, per ogni proprietà viene ricercato il parametro nella stringa di ricerca
     */
    static evaluateInterpolatedString(source:string, replacements: { [name: string]: any }): string {
        var replaced = source.replace(new RegExp('{([A-z]*)}', 'g'), m => {
            return replacements[m.substring(1, m.length - 1)] || '';
        });
        return replaced;
    }

    static uriBuilder(baseAddress:string, args: any = null) :string {
        let params:HttpParams = new HttpParams({fromObject: args});
        return baseAddress.concat("?", params.toString());
    }
}
