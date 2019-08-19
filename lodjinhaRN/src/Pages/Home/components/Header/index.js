import React from 'react';
import { View, Text, TouchableOpacity, ImageBackground } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import Styles from './styles';

const Header = ({ navigation }) => ({
  headerTitle: (
    <View style={Styles.headerTitleContainer}>
      <ImageBackground 
        style={Styles.headerTitleImage} 
        resizeMode="contain" 
        source={require('../../../../assets/images/logo_navbar.png')}/>
      <Text style={Styles.headerTitle}>a Lodjinha</Text>
    </View>
  ),
  headerLeft: (
    <TouchableOpacity 
      style={Styles.menuIcon}
      onPress={() => navigation.openDrawer()}>
      <Icon name="menu" color="#FFF" size={25}/>
    </TouchableOpacity>
  ),
  title: 'a lodjinha',
  headerTintColor: '#fff',
    headerStyle: {
      backgroundColor: '#5e2a84'
    }
});

export default Header;
