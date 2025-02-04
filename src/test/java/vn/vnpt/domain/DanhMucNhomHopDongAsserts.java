package vn.vnpt.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DanhMucNhomHopDongAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucNhomHopDongAllPropertiesEquals(DanhMucNhomHopDong expected, DanhMucNhomHopDong actual) {
        assertDanhMucNhomHopDongAutoGeneratedPropertiesEquals(expected, actual);
        assertDanhMucNhomHopDongAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucNhomHopDongAllUpdatablePropertiesEquals(DanhMucNhomHopDong expected, DanhMucNhomHopDong actual) {
        assertDanhMucNhomHopDongUpdatableFieldsEquals(expected, actual);
        assertDanhMucNhomHopDongUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucNhomHopDongAutoGeneratedPropertiesEquals(DanhMucNhomHopDong expected, DanhMucNhomHopDong actual) {
        assertThat(expected)
            .as("Verify DanhMucNhomHopDong auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucNhomHopDongUpdatableFieldsEquals(DanhMucNhomHopDong expected, DanhMucNhomHopDong actual) {
        assertThat(expected)
            .as("Verify DanhMucNhomHopDong relevant properties")
            .satisfies(e -> assertThat(e.getIdNhom()).as("check idNhom").isEqualTo(actual.getIdNhom()))
            .satisfies(e -> assertThat(e.getDienGiai()).as("check dienGiai").isEqualTo(actual.getDienGiai()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDanhMucNhomHopDongUpdatableRelationshipsEquals(DanhMucNhomHopDong expected, DanhMucNhomHopDong actual) {}
}
