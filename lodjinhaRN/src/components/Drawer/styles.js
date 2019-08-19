import { StyleSheet } from 'react-native';

const Styles = StyleSheet.create({
  container: { 
    flex: 1 
  },
  headerContainer: { 
    height: 60, 
    width: 60, 
    backgroundColor: '#f15025', 
    borderRadius: 50, 
    alignItems: 'center', 
    justifyContent: 'center',
    marginLeft: 16,
    marginTop: 32
  },
  imageBackground: { 
    height: 140, 
    width: '100%'
  },
  imageLogo: { 
    height: 40, 
    width: 40 
  },
  logoText:{ 
    color: '#FFF', 
    marginRight: 10, 
    fontFamily: 'Pacifico', 
    fontSize: 22,
    alignContent: 'flex-end', 
    alignSelf: 'flex-end'
  }
})

export default Styles;