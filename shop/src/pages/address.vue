<template>
  <x-header>确认订单</x-header>
  <group>
    <x-address :title="title" v-model="address" :list="addressData" placeholder="请选择地址" :show.sync="showAddress"></x-address>
    <x-input title="详细地址" name="address" placeholder="请输入详细地址" type="text" v-model="fullAddress"></x-input>
    <x-input title="邮编" name="code" type="number" :min="6" :max="6" v-model="code"></x-input>
    <x-input title="姓名" name="username" type="text" v-model="username"></x-input>
    <x-input title="手机号码" name="phone" keyboard="number" is-type="china-mobile" v-model="phone"></x-input>
  </group>
</template>

<script>
  import { Group, XAddress, ChinaAddressData, XInput, XHeader } from 'vux';

  export default {
    name: 'address',
    components: {
      Group,
      XAddress,
      XInput,
      XHeader
    },
    data () {
      return {
        title: '收货地址',
        addressData: ChinaAddressData,
        showAddress: false,
        address: [],
        fullAddress: '',
        code: '',
        username: '',
        phone: ''
      }
    }
  }
</script>

<style scoped>

</style>
