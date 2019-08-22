import { StyleSheet, Dimensions, Platform } from 'react-native';


var height = Dimensions.get('screen').height;
var width = Dimensions.get('screen').width;

const primaryColor = '#5e2a84';
const secondyColor = '#4a4a4a';
const defaultTextColor = secondyColor
const btnColor = "#E1E7EF";
const marginDefault = 15;
const fontFamilyRegular = 'Roboto-Regular';
const fontLogo = 'Pacifico Regular'
const grey = "#d5d5d5"
const greyMedium = "#aaaaaa"

var minimumDisplay =  360;

const BannerWidth = Dimensions.get('window').width;

export default{
    style: StyleSheet.create({

        mainContainer:{
            flex:1,
            paddingTop: 0
        },
        contanier:{
            paddingHorizontal: marginDefault
          },
          mainBanner: {
            flex: 1,
            height: 150,
            position: 'relative',
      
          },
          title:{
            color: defaultTextColor,
            fontSize: 23,
            fontFamily: fontFamilyRegular,
            marginLeft: 30,
            fontWeight: 'bold',
            paddingBottom: 5
          },
          priceProducto:{
            borderBottomWidth: 1,
            borderTopWidth: 1,
            borderColor: grey,
            marginTop: 10,
          },
          row:{
            marginBottom:30
          },
    }),
}

export { primaryColor,secondyColor, width as DeviceWidth, height as DeviceHeight, marginDefault,  btnColor ,  BannerWidth, grey , greyMedium ,fontLogo ,minimumDisplay }
