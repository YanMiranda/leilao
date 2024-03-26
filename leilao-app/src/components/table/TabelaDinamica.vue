<template>
  <div class="container-fluid">
    <div class="elevation-2" style="border-radius: 10px;">
      <v-data-table
        :sort-by="pagination.sortBy"
        hide-default-footer
        :page.sync="pagination.page"
        :items="items"
        :headers="headers"
        :items-per-page.sync="pagination.itemsPerPage "
        :server-items-length="pagination.total "
        @page-count="pagination.pageCount = $event"
      >
        <template v-slot:top>
          <v-toolbar flat class="pa-5">
            <v-toolbar-title :class="(isMobile) ? 'text-h4' : 'title-table'">{{ title }}</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn
              outlined
              elevation="2"
              color="success"
              :small="isMobile"
              @click="createItem"
            >
              <v-icon small>mdi-plus</v-icon>
              {{ isMobile ? '' : Adicionar }}
            </v-btn>
            <v-btn
              v-if="showSeachButton"
              outlined
              class="ml-2"
              elevation="2"
              color="primary"
              :small="isMobile"
              @click="serachItem"
            >
              <v-icon small>mdi-magnify</v-icon>
              {{ isMobile ? '' : Buscar }}
            </v-btn>
          </v-toolbar>
          <v-divider></v-divider>
        </template>
        <template v-slot:[`item.actions`]="{ item }">
          <div v-if="showbtnColumn">
            <v-icon
              small
              class="mr-2"
              @click="editRow(item)"
            >
              mdi-pencil
            </v-icon>
            <v-icon
              small
              @click="deleteRow(item)"
            >
              mdi-delete
            </v-icon>
          </div>
        </template>
      </v-data-table>
      <v-divider></v-divider>
      <div v-if="pagination && showItemsPerPage" class="text-center p-2">
          <v-pagination
            circle
            v-model="pagination.page"
            :length="pagination.pageCount"
            @next="nextPage"
            @previous="previusPage"
          />
            <v-text-field
                v-if="showItemsPerPage"
                min="-1"
                max="15"
                type="number"
                class="col-2"
                label="Items por página"
                :value.sync="pagination.itemsPerPage"
                @input="pagination.itemsPerPage = parseInt($event, 10)"
            />
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TabelaDinamica',

  props: {
    title: {
      type: String,
      required: true
    },
    headers: {
      type: Array,
      required: true,
    },
    items: {
      type: Array,
      required: true,
    },
    showbtnColumn: {
      type: Boolean,
      default: false,

    },
    showBtnAdd: {
      type: Boolean,
      default: false,
    },
    showSeachButton: {
      type: Boolean,
      default: false,
    },
    pagination: {
      type: Object,
      default() {
        return{}
      },
    },
    showItemsPerPage: {
      type: Boolean,
      default: true,
    },
  },

  watch: {
    pagination: {
      handler() {
        this.$emit('fetchData');
      },
      deep: true,
    },
  },

  methods: {
    editRow(index, item) {
      this.$emit('onEdit', index, item);
    },

    deleteRow(item) {
      this.$emit('onDelete', item);
    },

    createItem() {
      this.$emit('onAdd');
    },

    serachItem() {
      this.$emit('onSearch');
    },

    nextPage() {
      this.$emit('fetchData');
    },

    previusPage() {
      this.$emit('fetchData');
    },
  },
};
</script>

<style scoped>
.title-table {
  margin-bottom: 40px;
  text-align: center;
  color: #138496;
  margin-top: 20px;
  font-size: xx-large;
}

.text-h4 {
  color: #138496;
}
</style>
