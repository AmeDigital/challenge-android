import React, { Component } from 'react';
import { View, Text , TouchableOpacity  , ImageBackground , Image} from 'react-native';
import { Icon } from 'react-native-elements';

import { 
    FlexRow
} from '../common';

import Styles, { primaryColor , grey} from '../../Styles';
const { style } = Styles;

class MenuScreen extends Component {

    render(){
        return(
            <View>
                 <TouchableOpacity onPress={()=>{ this.props.navigation.closeDrawer()}} style={{ width: 50, height: 50, alignSelf: 'flex-end', top: 10, right: 20, position: 'absolute' , zIndex:99}}>
                    <View>
                    <Icon  name='ios-arrow-back' type='ionicon' color={grey} size={30} />
                    </View>
                </TouchableOpacity>
                <ImageBackground source={require('../assets/images/menu_pattern.png')} style={{width: '100%', height: 200}}>
                    <View style={{width: 70, height: 70, borderRadius: 50, backgroundColor:'#f15025', alignItems: 'center',justifyContent: 'center', marginTop: 40, marginLeft: 30,}}>
                        <Image source={require('../assets/images/logo_menu.png')} style={{width: 40, height: 40,}} />
                    </View>
                    <View style={{  alignItems: 'flex-end',marginRight: 40, marginTop: 30,}}>
                        <Image source={require('../assets/images/logo_menu1.png')} style={{width: 180, height: 50,}} />
                    </View>
                </ImageBackground>
                <View>
                <TouchableOpacity onPress={()=>{ this.props.navigation.closeDrawer()}}>
                    <FlexRow style={{ alignItems: 'center', marginLeft: 50, marginTop: 50,}}>
                        <Icon  name='ios-home' type='ionicon' color={primaryColor} size={30} />
                        <Text style={{marginLeft: 10, fontSize: 20,}}>Home</Text>
                    </FlexRow>
                </TouchableOpacity>
                <TouchableOpacity onPress={()=>{ this.props.navigation.navigate('about')}} >
                    <FlexRow style={{ alignItems: 'center', marginLeft: 50, marginTop: 50,}}>
                        <Icon  name='ios-pricetag' type='ionicon' color={primaryColor} size={30} />
                        <Text style={{marginLeft: 10, fontSize: 20,}}>Sobre o aplicativo</Text>
                    </FlexRow>
                </TouchableOpacity>
                </View>
            </View>
        )
    }

}

export default MenuScreen;