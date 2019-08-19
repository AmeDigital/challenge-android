import { MaskService } from 'react-native-masked-text';

export const formatNumber = (number) => {
  return MaskService.toMask('money', number, {
    unit: '',
    separator: ',',
    delimiter: '.'
 })
}