import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'stringToDate' })
export class StringToDate implements PipeTransform {
  transform(stringDate: string) {
    // Do the conversion, Formating, substring ... here
    let newDate = new Date(stringDate);
    console.log(newDate)
    return newDate ;
  }
}