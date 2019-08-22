import React, { Component } from 'react';
import { View, Text , Image } from 'react-native';


import Styles, { primaryColor , fontLogo} from '../../Styles';
const { style } = Styles;

class AboutScreen extends Component {

    static navigationOptions = {
        title: 'Sobre',
        headerStyle: {
          backgroundColor: primaryColor,
        },
        headerTintColor: '#fff',
        headerTitleStyle: {
          fontWeight: 'bold',
        },
      };

    render(){
        return(
            <View>
                <View style={{alignItems: 'center', marginTop: 100, }}>
                    <Image source={require('../assets/images/logo_sobre.png')} style={{width: 200, height: 200,}} />
                </View>
                <View style={{alignItems: 'center'}}>
                    <Image source={require('../assets/images/logo_sobre_txt.png')} style={{width: 310, height: 91, marginTop: 40,}} />
                </View>
                <View style={{ alignItems: 'center' , justifyContent:'flex-end',marginTop: 60,}}>
                    <Text style={{fontSize: 20, fontWeight: 'bold', marginBottom: 5,}}>Vanessa Bortoli</Text>
                    <Text>20 de agosto de 2019</Text>
                </View>
            </View>
        )
    }

}

export default AboutScreen;
