<template>
  <v-container fluid>
    <v-card elevation="3" :key="key">
      <v-container fluid>
        <p
          align="center"
          class="text-h4"
          style="padding: 15px; color: #138496;"
        >
          {{ title }}
        </p>
        <v-form v-model="valid">
          <div class="row">
            <v-col cols="12" md="4">
              <v-text-field
                clearable
                outlined
                pattern="[0-9]*"
                v-model="leilao.codigo"
                inputmode="numeric"
                label="Código do leilão"
                prepend-icon="mdi-format-color-highlight"
                onkeypress="return event.charCode >= 48 && event.charCode <= 57"
              />
            </v-col>

            <v-col cols="12" md="4">
              <v-text-field
                clearable
                outlined
                required
                :rules="vendedorRules"
                v-model="leilao.vendedor"
                label="Código do vendedor"
                prepend-icon="mdi-account-credit-card-outline"
              />
            </v-col>

            <v-col cols="12" md="4">
              <v-menu
                offset-y
                ref="menu"
                v-model="menu"
                max-width="290px"
                min-width="auto"
                transition="scale-transition"
                :close-on-content-click="false"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-on="on"
                    outlined
                    v-bind="attrs"
                    label="Inicio previsto"
                    prepend-icon="mdi-calendar"
                    v-model="dateFormated"
                    hint="Selecione a data de início do leilão"
                  />
                </template>
                <v-date-picker
                  no-title
                  format="dd/MM/yyyy"
                  v-model="dataPrevista"
                  @input="menu = false"
                />
              </v-menu>
            </v-col>

            <v-col cols="12">
              <v-textarea
                outlined
                required
                :counter="128"
                maxlength="128"
                :rules="descricaoRules"
                v-model="leilao.descricao"
                label="Descrição do leilão"
              />
            </v-col>
          </div>
        </v-form>
        <v-card-actions class="mt-3">
          <v-btn
            outlined
            elevation="2"
            color="error"
            @click="onClickCancelar"
          >
            <v-icon small>mdi-close</v-icon>
            Cancelar
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn
            outlined
            elevation="2"
            color="success"
            :disabled="!valid"
            @click="onClickSave"
          >
            <v-icon small>{{ leilao.id ? 'mdi-pencil' : 'mdi-plus' }}</v-icon>
            {{ leilao.id ? 'Editar' : 'Salvar'}}
          </v-btn>
        </v-card-actions>
      </v-container>
    </v-card>
    <SnackBar ref="SnackBar"/>
  </v-container>
</template>

<script>
import { formatdate, formatDateToRequest } from '@/util/DateUtil';
import SnackBar from '../../components/exception/SnackBar.vue';

export default {
  name: 'FormLeilao',

  components: {
    SnackBar,
  },

  data() {
    return{
      key: 0,
      menu: false,
      valid: false,
      leilao: this.resetItem(),
      dataPrevista: '',
      vendedorRules: [
        v => !!v || 'O vendedor é obrigatório',
        v => /^\d+$/.test(v) || 'Deve conter apenas números',
      ],
      descricaoRules: [
        v => !!v || 'A descrição é obrigatória',
      ],
      formatdate,
      formatDateToRequest,
    }
  },

  computed: {
    title() {
      return this.leilao.id ? 'Editar Leilão' : 'Cadastrar Leilão';
    },
    dateFormated() {
      return this.dataPrevista ? formatdate(this.dataPrevista) : '';
    }
  },

  watch: {
    'dataPrevista': function (val) {
      formatdate(val);
    }
  },

  methods: {
    resetItem() {
      return {
        id: null,
        codigo: '',
        vendedor: '',
        inicioPrevisto: '',
        descricao: '',
      }
    },

    async editForm(id) {
      await this.$axios.get(`/leiloes/${id}`)
        .then(response => {
          this.leilao =  response.data;
          this.dataPrevista = this.leilao.inicioPrevisto;
          this.key += 1;
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

    onClickCancelar() {
      this.$router.push('/leiloes');
      this.leilao = this.resetItem();
    },

    onClickSave() {
      if (this.valid) {
        const sendObj = {
          ...this.leilao,
          inicioPrevisto: formatDateToRequest(this.dataPrevista),
        };
        if (!this.leilao.id) {
          this.save(sendObj);
        } else {
          this.update(sendObj);
        }
      }
    },

    save(obj) {
      this.$axios.post('/leiloes', obj)
        .then(() => {
          this.$router.push('/leiloes');
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

    update(obj) {
      this.$axios.put(`/leiloes/${obj.id}`, obj)
        .then(() => {
          this.$router.push('/leiloes');
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

  },

  mounted() {
    if (this.$route.params.id) {
      this.editForm(this.$route.params.id);
    }
  },
}
</script>

<style>

</style>